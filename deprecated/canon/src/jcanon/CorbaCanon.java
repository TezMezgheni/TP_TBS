package jcanon;


/**
* jcanon/CorbaCanon.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/vpc/xprojects/gaming/canon/src/tn/rnu/enit/ateliercorba/jcanon/jcanon.idl
* lundi 15 octobre 2007 19 h 43 CEST
*/

public final class CorbaCanon implements org.omg.CORBA.portable.IDLEntity
{
  public int player = (int)0;
  public int score = (int)0;
  public int x = (int)0;
  public int y = (int)0;
  public int life = (int)0;
  public int mood = (int)0;

  public CorbaCanon ()
  {
  } // ctor

  public CorbaCanon (int _player, int _score, int _x, int _y, int _life, int _mood)
  {
    player = _player;
    score = _score;
    x = _x;
    y = _y;
    life = _life;
    mood = _mood;
  } // ctor

} // class CorbaCanon