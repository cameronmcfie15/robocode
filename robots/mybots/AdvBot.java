package mybots;

import robocode.*;
import robocode.util.Utils;

public class AdvBot extends AdvancedRobot {
    public void run() {
        // Unlock independent movement for gun and radar
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            setTurnRadarRight(360); // keep scanning
            setAhead(50);
            setTurnRight(20);
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // Turn gun toward enemy
        double turnAngle = getHeadingRadians() + e.getBearingRadians() - getGunHeadingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(turnAngle));

        // Fire based on distance
        if (e.getDistance() < 200) {
            setFire(3);
        } else if (e.getDistance() < 500) {
            setFire(2);
        } else {
            setFire(1);
        }
    }

    public void onHitWall(HitWallEvent e) {
        setBack(100);
    }
}
