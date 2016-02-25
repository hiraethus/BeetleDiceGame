package uk.ac.cf.cs.beetle;

public class DieRollToBodyPartMapping {
    private final Class<? extends IBodyPart> bodyPartClass;
    private final Integer dieRoll;

    public DieRollToBodyPartMapping(Integer dieRoll, Class<? extends IBodyPart> bodyPartClass) {
        this.dieRoll = dieRoll;
        this.bodyPartClass = bodyPartClass;
    }

    public Class<? extends IBodyPart> getBodyPartClass() {
        return bodyPartClass;
    }

    public Integer getDieRoll() {
        return dieRoll;
    }
}
