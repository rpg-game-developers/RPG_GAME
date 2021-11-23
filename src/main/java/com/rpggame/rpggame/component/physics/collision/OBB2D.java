package com.rpggame.rpggame.component.physics.collision;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;

public class OBB2D {
    private Vector2[] corner;
    private Vector2[] axis;
    private double[] origin;

    private boolean overlaps1Way(OBB2D other) {
        for (int a = 0; a < 2; ++a) {

            double t = other.corner[0].dot(axis[a]);

            // Find the extent of box 2 on axis a
            double tMin = t;
            double tMax = t;

            for (int c = 1; c < 4; ++c) {
                t = other.corner[c].dot(axis[a]);

                if (t < tMin) {
                    tMin = t;
                } else if (t > tMax) {
                    tMax = t;
                }
            }

            // We have to subtract off the origin

            // See if [tMin, tMax] intersects [0, 1]
            if ((tMin > 1 + origin[a]) || (tMax < origin[a])) {
                // There was no intersection along this dimension;
                // the boxes cannot possibly overlap.
                return false;
            }
        }

        // There was no dimension along which there is no intersection.
        // Therefore, the boxes overlap.
        return true;
    }

    /**
     * Updates the axes after the corners move.  Assumes the
     * corners actually form a rectangle.
     */
    private void computeAxes() {
        axis[0] = new Vector2(corner[1]);
        axis[1] = new Vector2(corner[3]);

        // Make the length of each axis 1/edge length so we know any
        // dot product must be less than 1 to fall within the edge.

        for (int a = 0; a < 2; ++a) {
            axis[a].sub(corner[0]);
            axis[a].scl(1.0f/axis[a].len2());
            origin[a] = corner[0].dot(axis[a]);
        }
    }

    public OBB2D(Vector2 size, Matrix3 transform) {
        this.corner = new Vector2[4];
        this.axis = new Vector2[2];
        this.origin = new double[2];
        this.corner[0] = new Vector2(0,0).mul(transform);
        this.corner[1] = new Vector2(size.x,0).mul(transform);
        this.corner[2] = new Vector2(size.x,size.y).mul(transform);
        this.corner[3] = new Vector2(0,size.y).mul(transform);
        computeAxes();
    }

    public boolean overlaps(OBB2D other) {
        return overlaps1Way(other) && other.overlaps1Way(this);
    }
}
