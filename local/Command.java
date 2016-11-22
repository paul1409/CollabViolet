package local;

import java.io.Serializable;

/**
 * A Command Interface
 * @author Bing Liang
 *
 */
public interface Command extends Serializable  {
  /**
   * Executes a command
   */
  public void execute();
}
