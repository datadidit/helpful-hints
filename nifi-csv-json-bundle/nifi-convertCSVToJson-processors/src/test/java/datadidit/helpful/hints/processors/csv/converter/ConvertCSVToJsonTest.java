/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package datadidit.helpful.hints.processors.csv.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.Before;
import org.junit.Test;


public class ConvertCSVToJsonTest {

    private TestRunner testRunner;

    @Before
    public void init() {
        testRunner = TestRunners.newTestRunner(ConvertCSVToJSON.class);
    }

    @Test
    public void testWithHeader() throws FileNotFoundException, UnsupportedEncodingException {
    	//Set Headers
    	testRunner.setProperty(ConvertCSVToJSON.HEADER, "true");
    	testRunner.enqueue(new FileInputStream(new File("src/test/resources/WithHeader.csv")));
    	testRunner.run();
    	
    	testRunner.assertAllFlowFilesTransferred(ConvertCSVToJSON.REL_SUCCESS, 1);
    	List<MockFlowFile> successFiles = testRunner.getFlowFilesForRelationship(ConvertCSVToJSON.REL_SUCCESS);
    
    	for(MockFlowFile mockFile : successFiles){
    		System.out.println(new String(mockFile.toByteArray(), "UTF-8"));
    	}
    }
    
    @Test
    public void testNoHeader() throws FileNotFoundException, UnsupportedEncodingException {
    	//Set Headers
    	testRunner.setProperty(ConvertCSVToJSON.HEADER, "false");
    	testRunner.setProperty(ConvertCSVToJSON.FIELD_NAMES, "firstName,lastName,dob");
    	testRunner.enqueue(new FileInputStream(new File("src/test/resources/NoHeader.csv")));
    	testRunner.run();
    	
    	testRunner.assertAllFlowFilesTransferred(ConvertCSVToJSON.REL_SUCCESS, 1);
    	List<MockFlowFile> successFiles = testRunner.getFlowFilesForRelationship(ConvertCSVToJSON.REL_SUCCESS);
    
    	for(MockFlowFile mockFile : successFiles){
    		System.out.println(new String(mockFile.toByteArray(), "UTF-8"));
    	}
    }

}
