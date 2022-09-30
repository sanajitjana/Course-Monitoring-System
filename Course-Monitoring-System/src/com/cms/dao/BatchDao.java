package com.cms.dao;

import java.util.List;

import com.cms.bean.Batch;
import com.cms.exceptions.BatchException;

public interface BatchDao {

	// checking batch name unique or not
	public boolean isNameUnique(String name) throws BatchException;

	// create batch
	public String createBatch(Batch batch) throws BatchException;

	// update batch by name
	public String upadteBatchByName(String old_name, Batch batch) throws BatchException;

	// view all batch details
	public List<Batch> viewAllBatchDetails() throws BatchException;

	// delete batch by name
	public String batchDeleteByName() throws BatchException;

}
