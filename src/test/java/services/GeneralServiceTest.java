package services;

import hospital.service.GeneralService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Oleksandr Belichenko
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneralServiceTest {

    @InjectMocks
    private GeneralService generalService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isValidStringDataEmpty(){
        String name = "";
        Assert.assertFalse(generalService.isValidStringData(name));
    }

    @Test
    public void isValidStringDataNull(){
        String name = null;
        Assert.assertFalse(generalService.isValidStringData(name));
    }

    @Test
    public void isValidStringDataNotNull(){
        String name = "ww";
        Assert.assertTrue(generalService.isValidStringData(name));
    }

    @Test
    public void getListFromString(){
        String name = "ww, a, b, h";
        Assert.assertEquals(4, generalService.getListFromString(name).size());
    }

    @Test
    public void convertStringToDateTime(){
        String date = "2000-01-01";
        String check = "2000-01-01T00:00:00.000+02:00";
        Assert.assertEquals(check, generalService.convertStringToDateTime(date).toString());
    }

}
