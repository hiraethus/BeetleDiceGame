package uk.ac.cf.cs.beetle;

import java.util.Collection;

interface BodyPartFactory {
    IBodyPart createBodyPart(Integer integer) throws InvalidDieValue;
    void addMapping(DieRollToBodyPartMapping mapping) throws InvalidDieRollToBodyPartMapping;
    Collection<DieRollToBodyPartMapping> getMappings();
}
