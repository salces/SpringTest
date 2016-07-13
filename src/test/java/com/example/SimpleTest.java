package com.example;

import com.example.pl.slc.service.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by slc on 07.07.16.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = ServiceConfig.class)
public class SimpleTest {

}
