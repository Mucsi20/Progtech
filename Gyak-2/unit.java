public class Unit{
  private String name;
  private int hp;
  private int attackDamage;
  public void Attack(Unit target){
    target.CalculateDamage(attackDamage);
  }
  public void TakeDamage(int damage){
    if(hp >= damage){
      hp-=damage;
    }
    else{
      hp = -1;
    }
  }
  public void CalculateDamage(int damage){
    TakeDamage(damage)
  }
}
public class Player extends Unit{
  private int defense;
  public void CalculateDamage(int damage){
    TakeDamage(damage/defense)
  }
}
public class Dragon extends Unit{

}

public class RedDragon extends Dragon{
  public void CalculateDamage(int damage){
    if(damage >= 60){
      TakeDamage(damage)
    }
  }
}

public class BlackDragon extends Dragon{
  public void CalculateDamage(int damage){
    if(damage >= 20){
      TakeDamage(damage)
    }
  }
}
public class Orc extends Unit{
  
}
public class Warrior extends Orc{

}
public class Berserker extends Orc{
  public void CalculateDamage(int damage){
    TakeDamage(damage*2)
  }
}
public class Guardian extends Orc{
  public void CalculateDamage(int damage){
    TakeDamage(damage/2)
  }
}