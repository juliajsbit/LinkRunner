package govitallLinkRunner;


import govitallLinkRunner.core.RobotProperties;
import govitallLinkRunner.core.results.RunResults;

public interface IGovitallRobot {

    /** Set all properties for run and site validation
     *
     * @param baseURL
     * @param  robotProperties*/

    void init(String baseURL, RobotProperties robotProperties);

    void run(int currentDepth, String coolie);

    /**Get results after validation run
     *
     * @return */

    RunResults getRunResults();

}
