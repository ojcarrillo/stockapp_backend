package com.stockapp.stockapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;

import com.stockapp.stockapp.util.JwtUtil;

public class MasterController {

	@Autowired
	JwtUtil jwtService;
	
	@Autowired
	ProjectionFactory projectionFactory;
}
