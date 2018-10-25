/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  PCA9685GpioExample.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  http://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2018 Pi4J
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
import java.math.BigDecimal;
import java.util.Scanner;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;


public class PCA9685GpioExample {

    private static final int MIN = 900;
    private static final int NEUTRAL = 1500;
    private static final int MAX = 2100;

    @SuppressWarnings("resource")
    public static void main(String args[]) throws Exception {
        System.out.println("<--Pi4J--> PCA9685 PWM Example ... started.");
        // This would theoretically lead into a resolution of 5 microseconds per step:
        // 4096 Steps (12 Bit)
        // T = 4096 * 0.000005s = 0.02048s
        // f = 1 / T = 48.828125
        BigDecimal frequency = new BigDecimal("48.828");
        // Correction factor: actualFreq / targetFreq
        // e.g. measured actual frequency is: 51.69 Hz
        // Calculate correction factor: 51.65 / 48.828 = 1.0578
        // --> To measure actual frequency set frequency without correction factor(or set to 1)
        BigDecimal frequencyCorrectionFactor = new BigDecimal("1.0578");
        // Create custom PCA9685 GPIO provider
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        final PCA9685GpioProvider provider = new PCA9685GpioProvider(bus, 0x40, frequency, frequencyCorrectionFactor);
        // Define outputs in use for this example
        GpioPinPwmOutput[] myOutputs = provisionPwmOutputs(provider);
        // Reset outputs
        provider.reset();

	provider.setPwm(PCA9685Pin.PWM_00, 1500);
	provider.setPwm(PCA9685Pin.PWM_01, 1500);
	provider.setPwm(PCA9685Pin.PWM_02, 1500);
	provider.setPwm(PCA9685Pin.PWM_03, 1500);
	provider.setPwm(PCA9685Pin.PWM_04, 1500);
	provider.setPwm(PCA9685Pin.PWM_05, 1500);
	provider.setPwm(PCA9685Pin.PWM_06, 1500);
	//provider.setPwm(PCA9685Pin.PWM_10, 1500);
	//provider.setPwm(PCA9685Pin.PWM_11, 1500);
	//provider.setPwm(PCA9685Pin.PWM_12, 1500);
	//provider.setPwm(PCA9685Pin.PWM_13, 1500);
	//provider.setPwm(PCA9685Pin.PWM_14, 1500);
	//provider.setPwm(PCA9685Pin.PWM_15, 1500);
	Thread.sleep(5000);
	provider.setPwm(PCA9685Pin.PWM_00, 1200);
	provider.setPwm(PCA9685Pin.PWM_01, 1200);
	provider.setPwm(PCA9685Pin.PWM_02, 1200);
	provider.setPwm(PCA9685Pin.PWM_03, 1200);
	provider.setPwm(PCA9685Pin.PWM_04, 1200);
	provider.setPwm(PCA9685Pin.PWM_05, 1200);
	provider.setPwm(PCA9685Pin.PWM_06, 1200);
	//provider.setPwm(PCA9685Pin.PWM_10, 1200);
	//provider.setPwm(PCA9685Pin.PWM_11, 1200);
	//provider.setPwm(PCA9685Pin.PWM_12, 1200);
	//provider.setPwm(PCA9685Pin.PWM_13, 1200);
	//provider.setPwm(PCA9685Pin.PWM_14, 1200);
	//provider.setPwm(PCA9685Pin.PWM_15, 1200);
	Thread.sleep(2000);




	System.out.println("Press <Enter> to terminate...");
        new Scanner(System.in).nextLine();

        System.out.println("Exiting PCA9685GpioExample");
   
        
  }

    private static GpioPinPwmOutput[] provisionPwmOutputs(final PCA9685GpioProvider gpioProvider) {
        GpioController gpio = GpioFactory.getInstance();
        GpioPinPwmOutput myOutputs[] = {
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_00, "Leg 1 Elbow"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_01, "Leg 1 Knee"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_02, "Leg 1 Foot"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_03, "Leg 2 Elbow"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_04, "Leg 2 Knee"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_05, "Leg 2 Foot"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_06, "Not Used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_07, "Not Used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_08, "Not Used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_09, "Not Used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_10, "Leg 3 Foot"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_11, "Leg 3 Knee"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_12, "Leg 3 Elbow"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_13, "Leg 4 Foot"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_14, "Leg 4 Knee"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_15, "Leg 4 Elbow")};
        return myOutputs;
    }
}
