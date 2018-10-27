package ecgberht.BehaviourTrees.Upgrade;

import ecgberht.GameState;
import org.iaie.btree.BehavioralTree;
import org.iaie.btree.task.leaf.Action;
import org.openbw.bwapi4j.type.UpgradeType;
import org.openbw.bwapi4j.unit.Armory;
import org.openbw.bwapi4j.unit.ResearchingFacility;

public class ChooseArmorMechUp extends Action {

    public ChooseArmorMechUp(String name, GameState gh) {
        super(name, gh);
    }

    @Override
    public BehavioralTree.State execute() {
        try {
            if (this.handler.UBs.isEmpty()) {
                return BehavioralTree.State.FAILURE;
            }
            for (ResearchingFacility u : this.handler.UBs) {
                if (!(u instanceof Armory)) continue;
                if (u.canUpgrade(UpgradeType.Terran_Vehicle_Plating) && !u.isResearching() && !u.isUpgrading() && this.handler.getPlayer().getUpgradeLevel(UpgradeType.Terran_Vehicle_Plating) < 3) {
                    this.handler.chosenUnitUpgrader = u;
                    this.handler.chosenUpgrade = UpgradeType.Terran_Vehicle_Plating;
                    return BehavioralTree.State.SUCCESS;
                }
            }
            return BehavioralTree.State.FAILURE;
        } catch (Exception e) {
            System.err.println(this.getClass().getSimpleName());
            e.printStackTrace();
            return BehavioralTree.State.ERROR;
        }
    }
}
