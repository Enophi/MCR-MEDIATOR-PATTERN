package ch.heig.component;


import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.app.DSLKt.play;
import static com.almasb.fxgl.app.DSLKt.spawn;

public class PlayerAction extends Component {
    private boolean canShoot = true;
    private double lastTimeShot = 0;
    private double attackSpeed = 2.0;

    @Override
    public void onUpdate(double tpf) {
        if (!canShoot) {
            if ((FXGL.getMasterTimer().getNow() - lastTimeShot) >= 1.0 / attackSpeed) {
                canShoot = true;
            }
        }
    }

    public  void shoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = FXGL.getMasterTimer().getNow();

        getEntity().getWorld().spawn("missile", getEntity().getX(), getEntity().getY());
    }
}
