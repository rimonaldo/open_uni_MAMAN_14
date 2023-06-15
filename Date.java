public class Date {
    private int _day;
    private int _month;
    private int _year;
    private final int DEFAULT_DAY = 1;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_YEAR = 2000;
    private final int JAN = 1;
    private final int FEB = 2;
    private final int MAR = 3;
    private final int APR = 4;
    private final int MAY = 5;
    private final int JUN = 6;
    private final int JUL = 7;
    private final int AUG = 8;
    private final int SEP = 9;
    private final int OCT = 10;
    private final int NOV = 11;
    private final int DEC = 12;
    private final int DAYS_IN_LEAP = 29;
    private final int DAYS_IN_NON_LEAP = 28;
    private final int MAX_DAYS_IN_MONTH = 31;

    public Date(final int day, final int month, final int year) {
        if (this.isValid(day, month, year)) {
            this._day = day;
            this._month = month;
            this._year = year;
        } else {
            this._day = 1;
            this._month = 1;
            this._year = 2000;
        }
    }

    public Date(final Date other) {
        this._day = other._day;
        this._month = other._month;
        this._year = other._year;
    }

    public int getYear() {
        return this._year;
    }

    public int getMonth() {
        return this._month;
    }

    public int getDay() {
        return this._day;
    }

    public void setYear(final int yearToSet) {
        if (this.isValid(this._day, this._month, yearToSet)) {
            this._year = yearToSet;
        }
    }

    public void setMonth(final int monthToSet) {
        if (this.isValid(this._day, monthToSet, this._year)) {
            this._month = monthToSet;
        }
    }

    public void setDay(final int dayToSet) {
        if (this.isValid(dayToSet, this._month, this._year)) {
            this._day = dayToSet;
        }
    }

    public boolean equals(final Date other) {
        return this._day == other._day && this._month == other._month && this._year == other._year;
    }

    public boolean before(final Date other) {
        return this._year < other._year || (this._year == other._year && this._month < other._month)
                || (this._year == other._year && this._month == other._month && this._day < other._day);
    }

    public boolean after(final Date other) {
        return other.before(this);
    }

    public int difference(final Date other) {
        return Math.abs(this.calculateDate(this._day, this._month, this._year)
                - this.calculateDate(other._day, other._month, other._year));
    }

    @Override
    public String toString() {
        String s = "";
        if (this._day < 10) {
            s = s + "0" + this._day + "/";
        } else {
            s = s + this._day + "/";
        }
        if (this._month < 10) {
            s = s + "0" + this._month + "/";
        } else {
            s = s + this._month + "/";
        }
        return s + this._year;
    }

    public Date tomorrow() {
        if (this.isValid(this._day + 1, this._month, this._year)) {
            return new Date(this._day + 1, this._month, this._year);
        }
        if (this.isValid(1, this._month + 1, this._year)) {
            return new Date(1, this._month + 1, this._year);
        }
        if (this.isValid(1, 1, this._year + 1)) {
            return new Date(1, 1, this._year + 1);
        }
        return null;
    }

    private boolean isValid(final int d, final int m, final int y) {
        if (!this.dayInRange(d) || !this.monthInRange(m) || !this.yearInRange(y)) {
            return false;
        }
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                return d <= 31;
            }
            case 2: {
                return (this.leap(y) && d <= 29) || (!this.leap(y) && d <= 28);
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                return d <= 30;
            }
            default: {
                return false;
            }
        }
    }

    private boolean dayInRange(final int d) {
        return 1 <= d && d <= 31;
    }

    private boolean monthInRange(final int m) {
        return 1 <= m && m <= 12;
    }

    private boolean yearInRange(final int y) {
        return 1000 <= y && y < 10000;
    }

    private boolean leap(final int y) {
        return false;
    }

    private int calculateDate(final int day, int month, int year) {
        if (month < 3) {
            --year;
            month += 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + (month + 1) * 306 / 10 + (day - 62);
    }
}