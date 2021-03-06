/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.jcr2vfsmigration.xml.model.asset;

public enum AssetType {

    DRL_MODEL( "model.drl" ),

    BUSINESS_RULE( "brl" ),

    DECISION_TABLE_GUIDED( "gdst" ),
    DRL( "drl" ),
    FUNCTION( "function" ),

    DECISION_SPREADSHEET_XLS( "xls" ),
    SCORECARD_SPREADSHEET_XLS( "scxls" ),
    PNG( "png" ),
    GIF( "gif" ),
    JPG( "jpg" ),
    PDF( "pdf" ),
    DOC( "doc" ),
    ODT( "odt" ),

    SCORECARD_GUIDED( "scgd" ),
    TEST_SCENARIO( "scenario" ),

    // Plain text assets
    ENUMERATION( "enumeration" ),
    DSL( "dsl" ),
    DSL_TEMPLATE_RULE( "dslr" ),
    RULE_TEMPLATE( "template" ),
    FORM_DEFINITION( "formdef" ),
    SPRING_CONTEXT( "springContext" ),
    SERVICE_CONFIG( "serviceConfig" ),
    WORKITEM_DEFINITION( "wid" ),
    CHANGE_SET( "changeset" ),
    RULE_FLOW_RF( "rf" ),
    BPMN_PROCESS( "bpmn" ),
    BPMN2_PROCESS( "bpmn2" ),
    FTL( "ftl" ),
    JSON( "json" ),
    FW( "fw" ),

    UNSUPPORTED( "misc" ),

    IGNORED( "ignore" );

    private String type;

    private AssetType( String type ) {
        this.type = type;
    }

    // Type the is being exported is the format String as in 5.6.x's AssetFormats class
    public static AssetType getByType( String type ) {
        if ( type != null && type.length() > 0 ) {;
            for ( AssetType assetType : values() ) {
                if ( type.equalsIgnoreCase( assetType.type ) ) return assetType;
            }
        }
        return UNSUPPORTED;
    }

    public String toString() {
        return type;
    }
}