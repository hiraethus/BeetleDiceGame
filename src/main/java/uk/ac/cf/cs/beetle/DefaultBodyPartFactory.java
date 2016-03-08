package uk.ac.cf.cs.beetle;


import uk.ac.cf.cs.beetle.exception.InvalidDieRollToBodyPartMapping;
import uk.ac.cf.cs.beetle.exception.InvalidDieValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DefaultBodyPartFactory implements BodyPartFactory {
    private final List<DieRollToBodyPartMapping> dieRollToBodyPartMappings = new ArrayList<>();

    public DefaultBodyPartFactory(List<DieRollToBodyPartMapping> mappings) throws InvalidDieRollToBodyPartMapping {
        for (DieRollToBodyPartMapping mapping : mappings) {
            this.addMapping(mapping);
        }
    }

    public DefaultBodyPartFactory() { }

    @Override
    public IBodyPart createBodyPart(Integer integer) throws InvalidDieValue {
        Optional<DieRollToBodyPartMapping> partOptional = dieRollToBodyPartMappings.stream()
                .filter(mapping -> mapping.getDieRoll() == integer)
                .findFirst();

        if (partOptional.isPresent()) {
            try {
                return partOptional.get().getBodyPartClass().newInstance();
            } catch (Exception e) { /* couldn't instantiate body part class */ }
        }

        throw new InvalidDieValue(
            String.format("%d is not an available die value for this factory", integer)
        );
    }

    @Override
    public void addMapping(DieRollToBodyPartMapping mapping) throws InvalidDieRollToBodyPartMapping {
        if (isBodyPartAlreadyAdded(mapping.getBodyPartClass())) {
            throw new InvalidDieRollToBodyPartMapping(
                    String.format("The %s class has already been added to this factory",
                            mapping.getBodyPartClass().getSimpleName())
            );
        }

        if (hasDieNumberAlready(mapping.getDieRoll())) {
            throw new InvalidDieRollToBodyPartMapping(
                    String.format("The die number %d has already been added to this factory",
                            mapping.getDieRoll())
            );
        }

        dieRollToBodyPartMappings.add(mapping);
    }

    @Override
    public Collection<DieRollToBodyPartMapping> getMappings() {
        return dieRollToBodyPartMappings;
    }

    private boolean isBodyPartAlreadyAdded(Class<? extends IBodyPart> bodyPartClass) {
        return dieRollToBodyPartMappings.stream()
                .map(mapping -> mapping.getBodyPartClass())
                .anyMatch(bodyPart -> bodyPart == bodyPartClass);

    }

    private boolean hasDieNumberAlready(int number) {
        return dieRollToBodyPartMappings.stream()
                .map(mapping -> mapping.getDieRoll())
                .anyMatch(dieRoll -> dieRoll == number);
    }
}
