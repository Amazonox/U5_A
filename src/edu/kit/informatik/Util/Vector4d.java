package edu.kit.informatik.Util;

public class Vector4d<T> {
    private  T left;
    private  T right;
    private  T up;

    private  T down;

    public Vector4d(final T left, final T right, final T up, final T down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }
    public Vector4d() {
    }

    public T getLeft() {
        return this.left;
    }

    public T getRight() {
        return this.right;
    }

    public T getUp() {
        return this.up;
    }

    public T getDown() {
        return this.down;
    }

    public void setLeft(final T left) {
        this.left = left;
    }

    public void setRight(final T right) {
        this.right = right;
    }

    public void setUp(final T up) {
        this.up = up;
    }

    public void setDown(final T down) {
        this.down = down;
    }

    public int count(){
        int count = 0;
        if(this.left != null) count++;
        if(this.right != null) count++;
        if(this.up != null) count++;
        if(this.down != null) count++;
        return count;
    }
}
