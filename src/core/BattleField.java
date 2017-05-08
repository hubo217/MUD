package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import abStract.Character;
import map.Room;
import role.Player;

public class BattleField implements ActionListener {
	
	private Timer attackTimerOne;
	private Timer attackTimerTwo;
	
	private Character attackerOne;
	private Character attackerTwo;

	public BattleField(Character one,Character two){
		this.attackerOne = one;
		this.attackerTwo = two;
		((Room) this.attackerOne.getLocation()).sayToRoom(attackerOne.getName() 
				+ "向"
				+ attackerTwo.getName()
				+ "发起了攻击");
		this.attackerOne.setIsFight(true);
		this.attackTimerOne = new Timer(1000, this);
		this.attackTimerOne.start();
	}
	public boolean fighting(){
		attackTimerOne.setDelay(5000);
		if(attackerOne.getRoomId() != attackerTwo.getRoomId()){
			attackTimerOne.stop();
			this.attackerOne.setIsFight(false);
			this.attackerTwo.setIsFight(false);
			return false;
		}
		/*
		*才此处的条件还需要更多改进
		*/
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.fighting()){
			attackerOne.attack(attackerTwo);
			attackerTwo.attack(attackerOne);
		}
	}
}
