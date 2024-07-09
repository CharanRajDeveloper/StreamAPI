package com.streams;

class A {
	public synchronized void foo(B b) {
		System.out.println("Threa1 execution of A class Run method");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread1 calling the B class last method");
		b.last();
	}

	public synchronized void last() {
		System.out.println("A class Last Method");
	}
}

class B {
	public synchronized void bar(A a) {
		System.out.println("Threa2 execution of B class Run method");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread2 calling the A class last method");
		a.last();
	}

	public synchronized void last() {
		System.out.println("B class Last Method");
	}

}

public class DeadLockDemo implements Runnable {
	 A a=new A();
	 B b=new B();
	 DeadLockDemo(){
		Thread t=new Thread(this);
		t.start();
		b.bar(a);
	}
	
	@Override
	public void run() {
		a.foo(b);
		
	}
	public static void main(String[] args) {
		new DeadLockDemo();
	}

}

//We can avoid deadlock by removing the synchronized keyword.