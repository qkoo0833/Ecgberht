package ecgberht.Upgrade;

import org.iaie.btree.state.State;
import org.iaie.btree.task.leaf.Action;
import org.iaie.btree.util.GameHandler;
import ecgberht.GameState;

import bwapi.TechType;
import bwapi.Unit;
import bwapi.UpgradeType;

public class ChooseStimUpgrade extends Action {

	public ChooseStimUpgrade(String name, GameHandler gh) {
		super(name, gh);
	}

	@Override
	public State execute() {
		try {
			if(((GameState)this.handler).UBs.isEmpty()) {
				return State.FAILURE;
			}
			for(Unit u : ((GameState)this.handler).UBs) {
				if(((GameState)this.handler).getPlayer().getUpgradeLevel(UpgradeType.U_238_Shells) == 1 && !((GameState)this.handler).getPlayer().hasResearched(TechType.Stim_Packs) && u.canResearch(TechType.Stim_Packs) && !u.isResearching() && !u.isUpgrading()) {
					((GameState)this.handler).chosenUnitUpgrader = u;
					((GameState)this.handler).chosenResearch = TechType.Stim_Packs;
					return State.SUCCESS;
				}
			}
			return State.FAILURE;
		} catch(Exception e) {
			System.err.println(this.getClass().getSimpleName());
			System.err.println(e);
			return State.ERROR;
		}
	}
}
