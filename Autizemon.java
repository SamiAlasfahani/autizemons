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
	
	public void attack(Gamestate g){
		//select target
		//target.takeDamage();
		//check kill
		//check victory
	}
	
	public void activateSkill(Gamestate g){
		skill.activate(g);
		//check victory
		skill.timerReset();
	}

	public void resetPhys(){stage_phys = 0; }
	public void resetSpec(){stage_spec = 0; }
	public void resetDef(){stage_def = 0; }
	public void resetSpdef(){stage_spdef = 0; }
	public void resetLuck(){stage_luck = 0; }
	public void resetSpeed(){stage_speed = 0;}
	
	public void maxPhys(){stage_phys = MAX_STAGE; }
	public void maxSpec(){stage_spec = MAX_STAGE; }
	public void maxDef(){stage_def = MAX_STAGE; }
	public void maxSpdef(){stage_spdef = MAX_STAGE; }
	public void maxLuck(){stage_luck = MAX_STAGE; }
	public void maxSpeed(){stage_speed = MAX_STAGE; }
	
	public void minPhys(){stage_phys = MIN_STAGE; }
	public void minSpec(){stage_spec = MIN_STAGE; }
	public void minDef(){stage_def = MIN_STAGE; }
	public void minSpdef(){stage_spdef = MIN_STAGE; }
	public void minLuck(){stage_luck = MIN_STAGE; }
	public void minSpeed(){stage_speed = MIN_STAGE; }
	
	public void resetAll(){
		resetPhys();
		resetSpec();
		resetDef();
		resetSpdef();
		resetLuck();
		resetSpeed();
	}
	
	public void refresh(){
		cur_type = base_type;
		cur_gender = base_gender;
		cur_skill = base_skill;
		cur_hp = base_hp;
		
		resetAll();
	}
}