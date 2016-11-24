package local;

import java.io.Serializable;

import com.horstmann.violet.framework.Graph;

/**
 * A Command Interface
 * @author Bing Liang
 *
 */
public interface Command extends Serializable  {
  /**
   * Executes a command
   */
  public void execute(Graph graph);
}
