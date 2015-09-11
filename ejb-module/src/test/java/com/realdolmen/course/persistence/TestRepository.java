package com.realdolmen.course.persistence;

import org.junit.Before;
import org.junit.Test;

public interface TestRepository {

    void find(int id);

    @Before
    void initializeRepository();

    @Test
    void testCreate();

    @Test
    void testRetrieveById();

    @Test
    void testUpdate();

    @Test
    void testDelete();

    @Test
    void testRefresh();
}
