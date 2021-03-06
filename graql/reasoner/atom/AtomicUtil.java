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
 *
 */

package grakn.core.graql.reasoner.atom;

import grakn.core.concept.answer.ConceptMap;
import grakn.core.graql.reasoner.atom.predicate.IdPredicate;
import grakn.core.kb.concept.manager.ConceptManager;
import grakn.core.kb.graql.reasoner.atom.Atomic;
import grakn.core.kb.graql.reasoner.query.ReasonerQuery;
import graql.lang.statement.Variable;

import javax.annotation.CheckReturnValue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helpers for handling and converting to and from Atoms
 */
public class AtomicUtil {
    /**
     * @param parent query context
     * @return (partial) set of predicates corresponding to this answer
     */
    @CheckReturnValue
    public static Set<Atomic> answerToPredicates(ConceptManager conceptManager, ConceptMap answer, ReasonerQuery parent) {
        Set<Variable> varNames = parent.getVarNames();
        return answer.map().entrySet().stream()
                .filter(e -> varNames.contains(e.getKey()))
                .map(e -> IdPredicate.create(conceptManager, e.getKey(), e.getValue().id(), parent))
                .collect(Collectors.toSet());
    }

}
