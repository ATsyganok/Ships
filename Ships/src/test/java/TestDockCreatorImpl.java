import com.atsyganok.creater.impl.DockCreatorImpl;
import com.atsyganok.object.Dock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDockCreatorImpl {

    private DockCreatorImpl dockCreator;

    private final int testCounter = 10;

    @Before
    public void initializeArray() {
        dockCreator = new DockCreatorImpl();
        dockCreator.create(testCounter);
    }

    @Test
    public void testGetDockArray() {
        Assert.assertTrue(testCounter == (dockCreator.getDockArray()).length);
        Assert.assertEquals("must return 10", testCounter, (dockCreator.getDockArray()).length);
        for(Dock dock:dockCreator.getDockArray()){
            Assert.assertTrue(dock!=null);
            Assert.assertTrue(dock.getClass().getSimpleName().equals("Dock"));
        }

    }

    @Test
    public void testCreate(){
        int c=5;
        DockCreatorImpl dc=new DockCreatorImpl();
        dc.create(c);
        Assert.assertEquals("must return 5",5,dc.getDockArray().length);
        Assert.assertTrue((dc.getDockArray())[4].getName().equals("dock_4"));
    }

    @After
    public void errase() {
        dockCreator = null;
    }
}
