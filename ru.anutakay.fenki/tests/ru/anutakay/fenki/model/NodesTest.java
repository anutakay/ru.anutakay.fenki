package ru.anutakay.fenki.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.model.thread.ThreadPool;

public class NodesTest {
    
    Size size;
    NodesImpl nodes;
    NodeIndex i;
    
    @Before
    public void createNodes() {
        NodeFactory factory = new NodeFactory();
        size = new Size();
        nodes = new NodesImpl(size, factory);
        i = new NodeIndex(0, 0);
    }

    @Test
    public void constructorEmptyTest() { 
        Node actual = nodes.getNode(i);
        Node expected = new NodeImpl();

        assertEquals(expected, actual);
    }
    
    @Test
    public void getSameObjectTest() {
        final Node one = nodes.getNode(i);
        final Node two = nodes.getNode(i);
        
        assertTrue(one == two);
    }

    @Test
    public void saveDataTest() {      
        final Node node = nodes.getNode(i);
        ThreadPool factory = new ThreadPool();
        node.setBegin(factory.createThread(), factory.createThread());
        node.setArrow(Arrow.LEFT_BACK);
        
        final Node node2 = nodes.getNode(i);
        
        assertEquals(node, node2);
    }

}
