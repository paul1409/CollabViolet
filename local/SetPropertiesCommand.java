package local;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.horstmann.violet.framework.Edge;
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
    private String method;
    private Class<?>[] classes;
    public SetPropertiesCommand(Object bean, Object value, String method,Class<?>[] classes) {        
        this.classes = classes;
        this.bean = bean;
        this.value = value;
        this.method = method;
    }

  @Override
  public void execute(Graph graph) {
      Object beSet = null;
      if(bean instanceof Node)  {
      ArrayList<Node> nodes = (ArrayList<Node>) (graph.getNodes());
      for (Node node : nodes) {
          if (node.getID() == ((Node)bean).getID()) {
           beSet = node;
          }
       }
      } else {
          Edge cache = (Edge)bean;
          ArrayList<Edge> edges = (ArrayList<Edge>) (graph.getEdges());
          for (Edge e : edges) {
              if (e.getStart().getID() == cache.getStart().getID() && e.getEnd().getID() == e.getEnd().getID()) {
               beSet = e;
              }
           }
      }
      try {
        Method m = bean.getClass().getMethod(method, classes);
        m.invoke(beSet, new Object[] { value });
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
    } catch (NoSuchMethodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
  }

}
