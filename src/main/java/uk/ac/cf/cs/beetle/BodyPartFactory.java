package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidDieRollToBodyPartMapping;
import uk.ac.cf.cs.beetle.exception.InvalidDieValue;

import java.util.Collection;

interface BodyPartFactory {
    IBodyPart createBodyPart(Integer integer) throws InvalidDieValue;
    void addMapping(DieRollToBodyPartMapping mapping) throws InvalidDieRollToBodyPartMapping;
    Collection<DieRollToBodyPartMapping> getMappings();
}
