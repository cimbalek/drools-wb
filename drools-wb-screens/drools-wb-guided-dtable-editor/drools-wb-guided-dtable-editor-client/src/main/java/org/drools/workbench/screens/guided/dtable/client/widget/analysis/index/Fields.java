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
package org.drools.workbench.screens.guided.dtable.client.widget.analysis.index;

import java.util.Collection;
import java.util.List;

import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.util.maps.MultiMap;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.Value;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.matchers.Matcher;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.select.Listen;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.select.Select;

public class Fields
        extends FieldsBase<Field> {


    public Where<FieldSelector, FieldListen> where( final Matcher matcher ) {
        return new Where<FieldSelector, FieldListen>() {
            @Override
            public FieldSelector select() {
                return new FieldSelector( matcher );
            }

            @Override
            public FieldListen listen() {
                return new FieldListen( matcher );
            }
        };
    }

    public class FieldSelector
            extends Select<Field> {

        public FieldSelector( final Matcher matcher ) {
            super( map.get( matcher.getKeyDefinition() ),
                   matcher );
        }

        public Conditions conditions() {
            final Conditions conditions = new Conditions();

            final MultiMap<Value, Field, List<Field>> subMap = asMap();
            if ( subMap != null ) {
                final Collection<Field> fields = subMap.allValues();
                for ( final Field field : fields ) {
                    conditions.merge( field.getConditions() );
                }
            }

            return conditions;

        }

        public Actions actions() {
            final Actions actions = new Actions();

            final MultiMap<Value, Field, List<Field>> subMap = asMap();
            if ( subMap != null ) {
                final Collection<Field> fields = subMap.allValues();
                for ( final Field field : fields ) {
                    actions.merge( field.getActions() );
                }
            }

            return actions;

        }
    }

    public class FieldListen
            extends Listen<Field> {

        public FieldListen( final Matcher matcher ) {
            super( map.get( matcher.getKeyDefinition() ),
                   matcher );
        }
    }

}
