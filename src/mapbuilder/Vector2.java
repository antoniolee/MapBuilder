/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapbuilder;

/**
 *
 * @author Ashwin
 */
public class Vector2 {
    public static Vector2 Zero = new Vector2(0,0);
    float x,y;
    
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public float distance(Vector2 v)
    {
        Vector2 a = v.sub(this);
        return (float)Math.sqrt( (a.x*a.x) + (a.y*a.y));
    }
    
    public double length()
    {
        return Math.sqrt( (x*x) + (y*y));
    }
    
    public void normalize()
    {
        if (length() != 0)
        {
            x = x/(float)length();
            y = y/(float)length();
        }
    }
    
    public Vector2 sum(Vector2 v)
    {
        return new Vector2(x + v.x, y + v.y);
    }
    
    public Vector2 sub(Vector2 v)
    {
        return new Vector2(x - v.x, y - v.y);
    }
    public Vector2 mult(float a)
    {
        return new Vector2(x * a, y * a);
    }
    
    public Vector2 div(float a)
    {
        return new Vector2(x * a, y * a); 
    }
}
