package me.michael

import kotlin.math.round

fun main(args: Array<String>){

    var input : Int
    add()

    do {
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPRSI()}")
            3 ->println("Monthly PAYE: ${getMonthlyPAYE()}")
            4 -> println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalMonthlyDeductions()}")
            6 -> println("Monthly Net Pay: ${getNetMonthlyPay()}")
            7 -> println(getPayslip())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun menu() : Int {
    print("""
         Employee Menu for ${getFullName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          -1. Exit
         Enter Option : """)
    return readLine()!!.toInt()
}

fun getFullName() = when (employee.gender){
    'm', 'M' -> "Mr. ${employee.firstName} ${employee.secondName}"
    'f', 'F'-> "Ms.  ${employee.firstName} ${employee.secondName}"
    else ->  "${employee.firstName} ${employee.secondName}"
}

fun getMonthlySalary() = roundTwoDecimals(employee.grossSalary / 12)
fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (employee.prsiPercentage / 100))
fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (employee.payePercentage / 100))
fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (employee.annualBonusAmountOf / 12))
fun getTotalMonthlyDeductions() = roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + employee.cycleToWorkSchemeMonthlyDeduction))
fun getNetMonthlyPay() = roundTwoDecimals(roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions()))

fun roundTwoDecimals(number: Double) = round(number * 100) / 100

var employee =  Employee("Joe", "Soap", 'm', 6143, 67543.21, 38.5, 5.2, 1450.50, 54.33)

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter employee ID: ")
    val employeeID = readLine()!!.toInt()
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employee = Employee(firstName, surname, gender, employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction)
}

fun getPayslip() =
        """            ----------------------------------------------------------
            |                     Monthly Payslip                    |
            |--------------------------------------------------------|
            |                                                        |
            |  Employee Name: ${getFullName()}       Employee ID: ${employee.employeeID}   |
            |                                                        |
            |--------------------------------------------------------|
            |  PAYMENT DETAILS           DEDUCTION DETAILS           |
            |--------------------------------------------------------|
            |  Salary: ${getMonthlySalary()}            PAYE: ${getMonthlyPAYE()}               |
            |  Bonus:  ${roundTwoDecimals(employee.annualBonusAmountOf/12)}            PRSI: ${getMonthlyPRSI()}                |
            |                            Cylce To Work: ${roundTwoDecimals(employee.cycleToWorkSchemeMonthlyDeduction)}        |
            |--------------------------------------------------------|
            |  Gross: ${getGrossMonthlyPay()}            Total Deductions:${getTotalMonthlyDeductions()}    |
            |--------------------------------------------------------|
            |                     NET PAY:${getNetMonthlyPay()}                    |
            ----------------------------------------------------------"""


