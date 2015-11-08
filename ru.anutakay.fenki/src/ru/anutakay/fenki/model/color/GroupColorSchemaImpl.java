package ru.anutakay.fenki.model.color;

import ru.anutakay.fenki.model.thread.Thread;

public class GroupColorSchemaImpl implements GroupColorSchema {
    
    public class GroupId {
        public int id;
        public GroupId(int id) {
            this.id = id;
        }
    }

    @Override
    public ColorID getColorID(final Thread threadID) {
        Integer a = new Integer((threadID.getId()) % 3);
        return new ColorID(a);
    }

}
