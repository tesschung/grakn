/*
 * GRAKN.AI - THE KNOWLEDGE GRAPH
 * Copyright (C) 2019 Grakn Labs Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package grakn.core.graph.graphdb.predicate;

import grakn.core.graph.graphdb.query.JanusGraphPredicate;

import java.util.List;

public class AndJanusPredicate extends ConnectiveJanusPredicate {

    private static final long serialVersionUID = -4249282297056862327L;

    public AndJanusPredicate() {
        super();
    }

    public AndJanusPredicate(List<JanusGraphPredicate> predicates) {
        super(predicates);
    }

    @Override
    ConnectiveJanusPredicate getNewNegateIntance() {
        return new OrJanusPredicate();
    }

    @Override
    boolean isOr() {
        return false;
    }

    @Override
    public boolean isQNF() {
        for (JanusGraphPredicate internalCondition : this) {
            if (!internalCondition.isQNF()) {
                return false;
            }
        }
        return true;
    }
}
