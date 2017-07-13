/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.dmn.client.shape.def;

import org.kie.workbench.common.dmn.api.definition.v1_1.InformationRequirement;
import org.kie.workbench.common.stunner.core.client.shape.view.HasTitle;

public final class InformationRequirementShapeDef
        implements DMNConnectorShapeDef<InformationRequirement> {

    @Override
    public double getAlpha(final InformationRequirement element) {
        return 1d;
    }

    @Override
    public String getBackgroundColor(final InformationRequirement element) {
        return "#000000";
    }

    @Override
    public double getBackgroundAlpha(final InformationRequirement element) {
        return 1;
    }

    @Override
    public String getBorderColor(final InformationRequirement element) {
        return "#000000";
    }

    @Override
    public double getBorderSize(final InformationRequirement element) {
        return 1d;
    }

    @Override
    public double getBorderAlpha(final InformationRequirement element) {
        return 1;
    }

    @Override
    public String getFontFamily(final InformationRequirement element) {
        return null;
    }

    @Override
    public String getFontColor(final InformationRequirement element) {
        return null;
    }

    @Override
    public String getFontBorderColor(final InformationRequirement element) {
        return null;
    }

    @Override
    public double getFontSize(final InformationRequirement element) {
        return 0;
    }

    @Override
    public double getFontBorderSize(final InformationRequirement element) {
        return 0;
    }

    @Override
    public HasTitle.Position getFontPosition(final InformationRequirement element) {
        return HasTitle.Position.BOTTOM;
    }

    @Override
    public double getFontRotation(final InformationRequirement element) {
        return 0;
    }

    @Override
    public Class<InformationRequirementShapeDef> getType() {
        return InformationRequirementShapeDef.class;
    }
}
