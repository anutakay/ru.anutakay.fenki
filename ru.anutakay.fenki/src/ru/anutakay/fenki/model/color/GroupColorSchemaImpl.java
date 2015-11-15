package ru.anutakay.fenki.model.color;

public class GroupColorSchemaImpl implements GroupColorSchema {
    
    public class GroupId {
        public int id;
        public GroupId(int id) {
            this.id = id;
        }
    }

    @Override
    public ColorID getColorID(final Integer threadID) {
        Integer a = threadID % 3; 
        return new ColorID(a);
    }

}
