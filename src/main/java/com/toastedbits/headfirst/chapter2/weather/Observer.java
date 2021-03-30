package com.toastedbits.headfirst.chapter2.weather;

public interface Observer {
	public void update(float temperature, float humidity, float pressure);
}
