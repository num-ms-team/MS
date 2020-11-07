package num.diplom.examination.model;

import num.diplom.base.model.EntityId;

public class ExamId extends EntityId {
    public ExamId(String id) {
        super(id);
    }

    public static ExamId valueOf(String id) {
        return new ExamId(id);
    }
}
