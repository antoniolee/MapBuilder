/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapbuilder;

import java.util.*;

/**
 *
 * @author Fernando & Ashwin
 */
public class Room {

    static Random rand = new Random();
    int width, height;
    int r, g, b;

    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;
    float maxSpeed = 3f;
    float minDistance = 40;
    float maxDistance = 200;
    float maxSteer = 0.5f;
    int gridSize = 4;
    BoundBox bBox;
    boolean settled = false;

    public static LinkedList<Room> roomList = new LinkedList<>();

    public Room(Vector2 position) {
        this.position = position;
        velocity = Vector2.Zero;
        acceleration = Vector2.Zero;
        
        //this.width = width;
        //this.height = height;
        bBox = new BoundBox(0, 0, 0, 0);
        //bBox.Width = width;
        //bBox.Height = height;
        
        bBox.Width = (int) (rand.nextDouble() * 7 + 2);
        bBox.Height = (int) (rand.nextDouble() * 2 - 1 + bBox.Width);
        this.width = bBox.Width;
        this.height = bBox.Height;
        
        //roomList.add(this);

            
    }

    class BoundBox {

        public int X, Y, Width, Height;

        public BoundBox(int x, int y, int width, int height) {
            X = x;
            Y = y;
            Width = width;
            Height = height;
        }

        public boolean intersects(BoundBox box) {
            BoundBox a = new BoundBox(X + Width / 2, Y + Height / 2, Width, Height);
            BoundBox b = new BoundBox(box.X + box.Width / 2, box.Y + box.Height / 2, box.Width, box.Height);

            return (Math.abs(a.X - b.X) * 2 < (a.Width + b.Width)) && (Math.abs(a.Y - b.Y) * 2 < (a.Height + b.Height));
        }
    }

    public void Update() {
        //ApplyForce(Seek(new Vector2(Mouse.GetState().X, Mouse.GetState().Y)) * 0.8f);
        ApplyForce(Separate().mult(1.0f));
        //ApplyForce(Cohesion() * 0.2f);

        velocity = velocity.sum(acceleration);
        if (velocity.length() > maxSpeed) {
            velocity.normalize();
            velocity = velocity.mult(maxSpeed);
        }
        position = position.sum(velocity);
        //System.out.println(velocity.x);
        if (velocity.length() != 0)
            settled = false;
        else
            settled = true;
        
        acceleration = Vector2.Zero;
        velocity = Vector2.Zero;

        bBox.X = (int) position.x;
        bBox.Y = (int) position.y;

    }

    public void ApplyForce(Vector2 force) {
        acceleration = acceleration.sum(force);
    }

    public Vector2 Seek(Vector2 target) {
        Vector2 steer = target.sub(position);
        steer.normalize();
        steer = steer.mult(maxSpeed);
        steer = steer.sub(velocity);
        if (steer.length() > maxSteer) {
            steer.normalize();
            steer = steer.mult(maxSteer);
        }
        return steer;
    }


    /*   public Vector2 Cohesion()
     {
     Vector2 average = Vector2.Zero;
     int averageCount = 0;
     for(Room r : roomList)
     {
     if (position.distance(b.position) < maxDistance)
     {
     average += b.position;
     averageCount++;
     }
     }

     if (averageCount != 0)
     {
     average = average / averageCount;
     return Seek(average);
     }
     else
     return position;

     }*/
    public Vector2 Separate() {
        Vector2 steer = new Vector2(0, 0);
        int count = 0;
        //Avoid other balls
        for (Room r : roomList) {
            float dist = position.distance(r.position);
            if (bBox.intersects(r.bBox) && this != r) {
                Vector2 v = new Vector2((r.position.x - position.x), (r.position.y - position.y)).mult(-1);
                v.normalize();
                v = v.div(dist);
                steer = steer.sum(v);
                count++;
            }
        }
        
        if (count > 0) {
            steer = steer.div(count);
        }
        if (steer.length() > 0) {
            steer.normalize();
            steer = steer.mult(maxSpeed);
            steer = steer.sub(velocity);
            if (steer.length() > maxSteer) {
                steer.normalize();
                steer = steer.mult(maxSteer);
            }
        }

        return steer;
    }
}
