package uk.ac.cf.cs.beetle;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultBodyPartFactory implements BodyPartFactory {
    private final List<DieRollToBodyPartMapping> dieRollToBodyPartMappings = new ArrayList<>();

    @Override
    public IBodyPart createBodyPart(Integer integer) throws InvalidDieValue {
        return null;
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
