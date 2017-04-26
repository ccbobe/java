package com.bobe.webservice;

import javax.jws.WebService;

@WebService(endpointInterface="com.bobe.webservice.Add")
public class AddImpl implements Add {

	@Override
	public double add(double x, double y) {
		double sum=0;
		sum=x+y;
		return sum;
	}

}
