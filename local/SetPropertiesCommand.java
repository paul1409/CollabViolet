package local;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.horstmann.violet.framework.Graph;
import com.horstmann.violet.framework.Node;

/**
 * Sets properties
 * @author Ruiyang Wang
 *
 */
public class SetPropertiesCommand implements Command {
    private Object bean;
    private Object value;
    private Method method;
    public SetPropertiesCommand(Object bean, Object value, Method method) {
        this.bean = bean;
        this.value = value;
        this.method = method;
    }

  @Override
  public void execute(Graph graph) {
      ArrayList<Node> nodes = (ArrayList<Node>) (graph.getNodes());
      Node nodeBeSet = null;
      for (Node node : nodes) {
          if (node.getID() == ((Node)bean).getID()) {
            nodeBeSet = node;
          }
       }
      try {
        method.invoke(bean, new Object[] { value });
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
    }
  }

}
