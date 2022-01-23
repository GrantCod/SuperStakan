package com.company.functions.tools;

import com.company.functions.FunctionPoint;

public class MyLinkedList {
    private class FunctionNode {
        FunctionPoint point;
        FunctionNode previousPoint;
        FunctionNode nextPoint;


        public FunctionNode() {
            this.point = null;
            this.previousPoint = null;
            this.nextPoint = null;
        }

        public FunctionNode(FunctionPoint point) {
            this.point = point;
            this.previousPoint = null;
            this.nextPoint = null;
        }

        public FunctionNode(FunctionPoint point, FunctionNode previousPoint, FunctionNode nextPoint) {
            this.point = point;
            this.previousPoint = previousPoint;
            this.nextPoint = nextPoint;
        }

    }

    private FunctionNode head;
    private int pointsCount;

    public MyLinkedList() {
        this.head = new FunctionNode();
        this.head.previousPoint = head;
        this.head.nextPoint = head;
        this.pointsCount = 0;
    }

    private FunctionNode getNodeByIndex(int index) {
        FunctionNode currentNode = head.nextPoint;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextPoint;
        }
        return currentNode;
    }

    private FunctionNode addNodeToTail() {
        FunctionNode tail = this.head.previousPoint;
        FunctionNode tail1 = new FunctionNode();
        tail.nextPoint = tail1;
        this.head.previousPoint = tail1;
        tail1.previousPoint = tail;
        tail1.nextPoint = head;
        this.pointsCount++;
        return tail1;
    }

    private FunctionNode addNodeByIndex(int index) {
        FunctionNode node = new FunctionNode();
        FunctionNode nodeByIndex = this.getNodeByIndex(index - 1);
        FunctionNode nodeByIndex1 = this.getNodeByIndex(index);

        node.previousPoint = nodeByIndex;
        node.nextPoint = nodeByIndex1;
        nodeByIndex.nextPoint = node;
        nodeByIndex1.previousPoint = node;
        this.pointsCount++;
        return node;
    }

    private FunctionNode deleteNodeByIndex(int index) {

        FunctionNode nodePrevious = this.getNodeByIndex(index - 1);
        FunctionNode node = this.getNodeByIndex(index);
        FunctionNode nodeNext = this.getNodeByIndex(index+1);
        nodePrevious.nextPoint = nodeNext;
        nodeNext.previousPoint = nodePrevious;
        this.pointsCount--;
        return node;
    }

    public FunctionPoint getPointByIndex(int index) {
        FunctionNode node = this.getNodeByIndex(index);
        return node.point;
    }

    public void addPointToTail(FunctionPoint functionPoint) {
        FunctionNode node = addNodeToTail();
        node.point = functionPoint;
    }

    public void addPointByIndex(int index, FunctionPoint functionPoint) {
        FunctionNode node;
        if (index >= pointsCount) {
             node = this.addNodeToTail();
        }
        else {
            node = this.addNodeByIndex(index);
        }
        node.point = functionPoint;
    }
    public void deletePointByIndex(int index) {
        this.deleteNodeByIndex(index);
    }
    public int getLength() {
        return this.pointsCount;
    }
    public void setPointByIndex(int index, FunctionPoint functionPoint) {
        FunctionNode nodeByIndex = getNodeByIndex(index);
        nodeByIndex.point = functionPoint;
    }

}
