package org.jboss.errai.ioc.tests.rebind;

import org.jboss.errai.ioc.rebind.ioc.codegen.Statement;
import org.jboss.errai.ioc.rebind.ioc.codegen.builder.StatementBuilder;
import org.jboss.errai.ioc.rebind.ioc.codegen.exception.InvalidTypeException;
import org.jboss.errai.ioc.rebind.ioc.codegen.exception.TypeNotIterableException;
import org.jboss.errai.ioc.rebind.ioc.codegen.exception.UndefinedVariableException;
import org.junit.Test;

import javax.enterprise.util.TypeLiteral;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Tests the generation of loops with our {@link StatementBuilder} API.
 *
 * @author Christian Sadilek <csadilek@redhat.com>
 */
public class LoopStatementBuilderTest {

    @Test
    public void testLoop() throws Exception {

        Statement createObject = StatementBuilder.create()
                .newObject(Integer.class);

        Statement createAnotherObject = StatementBuilder.create()
                .newObject(Integer.class);

        Statement loop = StatementBuilder.create()
                .loadVariable("list", new TypeLiteral<List<String>>() {
                })
                .foreach("element")
                .addStatement(createObject)
                .addStatement(createAnotherObject);

        Statement loopWithArray = StatementBuilder.create()
                .loadVariable("list", String[].class)
                .foreach("element")
                .addStatement(createObject)
                .addStatement(createAnotherObject);

        Statement loopWithList = StatementBuilder.create()
                .loadVariable("list", List.class)
                .foreach("element")
                .addStatement(createObject)
                .addStatement(createAnotherObject);

        System.out.println(loop.generate());
        System.out.println(loopWithArray.generate());
        System.out.println(loopWithList.generate());
    }

    @Test
    public void testLoopWithProvidedLoopVarType() throws Exception {
        Statement loop = StatementBuilder.create()
                .loadVariable("list", new TypeLiteral<List<String>>() {
                })
                .foreach("element", Object.class, "list");

        System.out.println(loop.generate());

        try {
            StatementBuilder.create()
                    .loadVariable("list", new TypeLiteral<List<String>>() {
                    })
                    .foreach("element", Integer.class)
                    .generate();
            fail("Expected InvalidTypeException");
        } catch (InvalidTypeException ite) {
            // expected
            System.out.println(ite.getMessage());
        }
    }

    @Test
    public void testNestedLoops() throws Exception {
        Statement createObject = StatementBuilder.create().newObject(Integer.class);

        Statement outerLoop = StatementBuilder.create()
                .loadVariable("list", new TypeLiteral<List<String>>() {
                })
                .foreach("element")
                .addStatement(StatementBuilder.create()
                        .loadVariable("list2", new TypeLiteral<List<String>>() {
                        })
                        .foreach("element2", "list2")
                        .addStatement(createObject)
                );

        System.out.println(outerLoop.generate());
    }

    @Test
    public void testNestedLoopsWithInvalidVariable() throws Exception {
        Statement createObject = StatementBuilder.create().newObject(Integer.class);

        // uses a not existing list in inner loop -> should fail with UndefinedVariableExcpetion
        try {
            StatementBuilder.create()
                    .loadVariable("list", List.class)
                    .foreach("element", "list")
                    .addStatement(StatementBuilder.create()
                            .loadVariable("list2", new TypeLiteral<List<String>>() {
                            })
                            .foreach("element2", "listDoesNotExist")
                            .addStatement(createObject)
                    )
                    .generate();
            fail("Expected UndefinedVariableException");
        } catch (UndefinedVariableException ude) {
            // expected
            System.out.println(ude.getMessage());
        }
    }

    @Test
    public void testLoopWithInvalidSequenceType() throws Exception {

        try {
            StatementBuilder.create()
                    .loadVariable("list", String.class)
                    .foreach("element")
                    .generate();
            fail("Expected TypeNotIterableException");
        } catch (TypeNotIterableException tnie) {
            // expected
            System.out.println(tnie.getMessage());
        }
    }
}
