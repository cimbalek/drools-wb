/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.analysis;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.controller.AnalyzerController;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.controller.AnalyzerControllerImpl;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.panel.AnalysisReportScreen;
import org.drools.workbench.screens.guided.dtable.service.GuidedDecisionTableEditorService;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.uberfire.mvp.PlaceRequest;

@Dependent
public class DecisionTableAnalyzerProvider {

    private final AnalysisReportScreen analysisReportScreen;

    @Inject
    public DecisionTableAnalyzerProvider( final AnalysisReportScreen analysisReportScreen ) {
        this.analysisReportScreen = analysisReportScreen;
    }

    public AnalyzerController newAnalyzer( final PlaceRequest placeRequest,
                                           final AsyncPackageDataModelOracle oracle,
                                           final GuidedDecisionTable52 model,
                                           final EventBus eventBus ) {
        if ( isAnalysisEnabled() ) {
            return new AnalyzerControllerImpl( new DecisionTableAnalyzerBuilder()
                                                       .withReportScreen( analysisReportScreen )
                                                       .withPlaceRequest( placeRequest )
                                                       .withOracle( oracle )
                                                       .withModel( model )
                                                       .build(),
                                               eventBus );
        } else {
            return makePlaceHolder();
        }
    }

    private boolean isAnalysisEnabled() {
        if ( ApplicationPreferences.getStringPref( GuidedDecisionTableEditorService.DTABLE_VERIFICATION_DISABLED ) != null ) {
            return !ApplicationPreferences.getBooleanPref( GuidedDecisionTableEditorService.DTABLE_VERIFICATION_DISABLED );
        } else {
            return true;
        }
    }

    private AnalyzerController makePlaceHolder() {
        return new AnalyzerController() {
            @Override
            public void initialiseAnalysis() {

            }

            @Override
            public void terminateAnalysis() {

            }
        };
    }
}
