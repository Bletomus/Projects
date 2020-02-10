
#Suppose the cover price of a book is $24.95, but bookstores get a 40% discount. Shipping costs
#$3 for the first copy and 75 cents for each additional copy. What is the total wholesale cost for
#60 copies?
if __name__ == '__main__':
    cost = 24.95
    discount = 0.40
    shipping_cost_one = 3.00
    shipping_cost_rest = 0.75
    amount = 60
    amountShipping = 59
    costForEach = cost - cost * discount
    totalUnitCost = costForEach * amount
    totalShipping = shipping_cost_one + shipping_cost_rest * amountShipping
    totalCost = totalUnitCost + totalShipping
    print("{:<15}".format("Discount") + "\t" + "{:<15}".format("Wholesale Price for 60"))
    print("{:<15}".format(str(cost * discount)) + "\t" + "{:.2f}".format(totalCost))
    