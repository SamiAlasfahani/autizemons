import java.util.*;

public class Autizemon{
	
	public static int MAX_STAGE = 4;
	public static int MIN_STAGE = -4;
	
	private String name;
	private Status status;
	private boolean isAlive;
	//
	private Type base_type;
	private boolean base_gender;
	private Skill base_skill;
	private int base_hp;
	private int base_phys;
	private int base_spec;
	private int base_def;
	private int base_spdef;
	private int base_luck;
	private int base_speed;
	//
	private Type cur_type;
	private boolean cur_gender;
	private Skill cur_skill;
	private int cur_hp;
	//
	private int stage_phys;
	private int stage_spec;
	private int stage_def;
	private int stage_spdef;
	private int stage_luck;
	private int stage_speed;
	
	public Autizemon(String _name, Type type, boolean gender, Skill skill, Status _status, int hp, int phys, int spec, int def, int spdef, int luck, int speed){
		name = _name;
		status = new Status("healthy");
		
		isAlive = true;
		base_type = type;
		base_gender = gender;
		base_skill = skill;
		base_hp = hp;
		base_phys = phys;
		base_spec = spec;
		base_def = def;
		base_spdef = spdef;
		base_luck = luck;
		base_speed = speed;
		
		cur_type = type;
		cur_gender = gender;
		cur_skill = skill;
		cur_hp = hp;
		
		stage_phys = 0;
		stage_spec = 0;
		stage_def = 0;
		stage_spdef = 0;
		stage_luck = 0;
		stage_speed = 0;
	}
	
	public void setType(Type newType){cur_type = newType; }
	public void setGender(boolean newGender){cur_gender = newGender; }
	public void setSkill(Skill newSkill){cur_skill = newSkill; }
	public void setStatus(Status newStatus){
		if(!status.type().equals("healthy")) return;
		status.setStatus(newStatus);
	}
	
	public void boostPhys(int newBoost){
		if(stage_phys + newBoost > MAX_STAGE) return;
		stage_phys += newBoost;
	}
	
	public void boostSpec(int newBoost){
		if(stage_spec + newBoost > MAX_STAGE) return;
		stage_spec += newBoost;
	}
	
	public void boostDef(int newBoost){
		if(stage_def + newBoost > MAX_STAGE) return;
		stage_def += newBoost;
	}
	
	public void boostSpdef(int newBoost){
		if(stage_spdef + newBoost > MAX_STAGE) return;
		stage_spdef += newBoost;
	}
	
	public void boostLuck(int newBoost){
		if(stage_luck + newBoost > MAX_STAGE) return;
		stage_luck += newBoost;
	}
	
	public void boostSpeed(int newBoost){
		if(stage_speed + newBoost > MAX_STAGE) return;
		stage_speed += newBoost;
	}
	//
	public void knockPhys(int newBoost){
		if(stage_phys - newBoost < MIN_STAGE) return;
		stage_phys -= newBoost;
	}
	
	public void knockSpec(int newBoost){
		if(stage_spec - newBoost < MIN_STAGE) return;
		stage_spec -= newBoost;
	}
	
	public void knockDef(int newBoost){
		if(stage_def - newBoost < MIN_STAGE) return;
		stage_def -= newBoost;
	}
	
	public void knockSpdef(int newBoost){
		if(stage_spdef - newBoost < MIN_STAGE) return;
		stage_spdef -= newBoost;
	}
	
	public void knockLuck(int newBoost){
		if(stage_luck - newBoost < MIN_STAGE) return;
		stage_luck -= newBoost;
	}
	
	public void knockSpeed(int newBoost){
		if(stage_speed - newBoost < MIN_STAGE) return;
		stage_speed -= newBoost;
	}
	
	public void takeDamage(int damage){

		cur_hp -= damage;
		if(cur_hp <= 0){
			if(cur_hp < 0) cur_hp = 0;
			isAlive = false;
		}
	}
	
	public void receiveHealing(int heal){
		cur_hp += heal;
		if(cur_hp > base_hp) cur_hp = base_hp;
	}

	public void timerDown(int amount){skill.timerDown(amount);}
	public void timerUp(int amount){skill.timerUp(amount);}
	
	public boolean attack(int myPos, ArrayList<Autizemon> enemies){
		Autizemon target;
		if(enemies.get(myPos).isAlive) target = enemies.get(myPos);
		else if(!enemies.get(myPos).isAlive && enemies.get(1-myPos)) target = enemies.get(1-myPos);
		else {
			System.out.println("The attack failed!");
			return;
		}
		
		int damage = calculateDamage();
		target.takeDamage(damage);
		System.out.println("Hit for " + damage + " damage!");
		
		return target.isAlive;
	}
	
	public void activateSkill(int myPos, Autizemon ally, ArrayList<Autizemon> enemies){
		skill.activate(myPos, Autizemon ally, ArrayList<Autizemon> enemies);
		//check victory
		skill.timerReset();
	}
	
	public static int calculateDamage(int attack, int defense, Type attackerType, Type defenderType, boolean isCritical){
		int damage = ((4200*(attack/defense))/50)+2;
		damage *= 0.85 + math.Random() * (1 - 0.85);
		if(isCritial) damage *= 2;
		if(defenderType.weakness.equals(attackerType.name)) damage *= 2;
	}
	
	public void modifyStatStage(int statStage, int amount){
		if(amount < 0){
			for(int i = 0; i<Math.abs(amount); i++){
				if(statStage - 1 < MIN_STAGE){
					System.out.println("But it's stats couldn't get any lower!");
					break;
				}
				statStage--;
			}
			return;
		}
		
		for(int i = 0; i<amount; i++){
			if(statStage + 1 > MAX_STAGE){
				System.out.println("But it's stats were already maxed out!");
				break;
			}
			statStage++;
		}
		return;
	}
	public void resetStatStage(int statStage){statStage = 0;}
	public void maxStatStage(int statStage){statStage = MAX_STAGE;}
	public void minStatStage(int statStage){statStage = MIN_STAGE;}
	public int calcCurStat(int base, int stage){return stage >= 0 ? base + (0.5 * stage * base) : base + (-0.10 * stage * base);}
	
	public void resetAll(){
		resetStatStage(stage_phys);
		resetStatStage(stage_spec);
		resetStatStage(stage_def);
		resetStatStage(stage_spdef);
		resetStatStage(stage_luck);
		resetStatStage(stage_speed);
	}
	
	public void refresh(){
		cur_type = base_type;
		cur_gender = base_gender;
		cur_skill = base_skill;
		cur_hp = base_hp;
		
		resetAll();
	}
}