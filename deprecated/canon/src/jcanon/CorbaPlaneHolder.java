package jcanon;

/**
* jcanon/CorbaPlaneHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/vpc/xprojects/gaming/canon/src/tn/rnu/enit/ateliercorba/jcanon/jcanon.idl
* lundi 15 octobre 2007 19 h 43 CEST
*/

public final class CorbaPlaneHolder implements org.omg.CORBA.portable.Streamable
{
  public jcanon.CorbaPlane value = null;

  public CorbaPlaneHolder ()
  {
  }

  public CorbaPlaneHolder (jcanon.CorbaPlane initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = jcanon.CorbaPlaneHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    jcanon.CorbaPlaneHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return jcanon.CorbaPlaneHelper.type ();
  }

}
