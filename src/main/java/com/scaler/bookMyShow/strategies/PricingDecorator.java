package com.scaler.bookMyShow.strategies;

public abstract class PricingDecorator implements PricingStrategy{

    protected PricingStrategy pricingStrategy;

    public PricingDecorator(PricingStrategy pricingStrategy){
        this.pricingStrategy = pricingStrategy;
    }

}
