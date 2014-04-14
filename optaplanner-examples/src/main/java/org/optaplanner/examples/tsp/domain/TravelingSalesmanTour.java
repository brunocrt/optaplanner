/*
 * Copyright 2011 JBoss Inc
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

package org.optaplanner.examples.tsp.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simplelong.SimpleLongScore;
import org.optaplanner.core.impl.score.buildin.simplelong.SimpleLongScoreDefinition;
import org.optaplanner.core.impl.solution.Solution;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.persistence.xstream.XStreamScoreConverter;

@PlanningSolution
@XStreamAlias("TravelingSalesmanTour")
public class TravelingSalesmanTour extends AbstractPersistable implements Solution<SimpleLongScore> {

    private String name;
    private List<City> cityList;
    private List<Domicile> domicileList;

    private List<Visit> visitList;

    @XStreamConverter(value = XStreamScoreConverter.class, types = {SimpleLongScoreDefinition.class})
    private SimpleLongScore score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @ValueRangeProvider(id = "domicileRange")
    public List<Domicile> getDomicileList() {
        return domicileList;
    }

    public void setDomicileList(List<Domicile> domicileList) {
        this.domicileList = domicileList;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "visitRange")
    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }

    public SimpleLongScore getScore() {
        return score;
    }

    public void setScore(SimpleLongScore score) {
        this.score = score;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(cityList);
        facts.addAll(domicileList);
        // Do not add the planning entity's (visitList) because that will be done automatically
        return facts;
    }

}
