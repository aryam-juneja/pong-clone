public class Ball {
    public Rect ball;
    public Rect leftPaddle, rightPaddle;
    public int leftScore, rightScore;

    public double vx = -1.0;
    public double vy = 1.0;

    public Ball(Rect ball, Rect leftPaddle, Rect rightPaddle, int leftScore, int rightScore) {
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScore = leftScore;
        this.rightScore = rightScore;
    }

    public double calculateVelocityAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height / 2)) - (this.ball.y + this.ball.height/ 2);
        double normalIntersectY = relativeIntersectY / (paddle.height /2);
        double theta = normalIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt) {
        if (vx < 0) {
            if (this.ball.x <= this.leftPaddle.x + this.leftPaddle.width && this.ball.x >= this.leftPaddle.x &&
                this.ball.y + this.ball.height >= this.leftPaddle.y &&
                this.ball.y <= this.leftPaddle.y + this.leftPaddle.height) {

                double theta = calculateVelocityAngle(leftPaddle);
                double newVx = Math.abs(Math.cos(theta));
                double newVy = (-Math.sin(theta));

                double oldSign = Math.signum(vx);
                this.vx = newVx*-1*oldSign;
                this.vy = newVy;
            } else if (this.ball.x + this.ball.width < this.leftPaddle.x) {
                System.out.println("The player has lost a point");
            }
        }else if (vx > 0) {
            if (this.ball.x + this.ball.width >= this.rightPaddle.x && this.ball.x <= this.rightPaddle.x &&
                this.ball.y >= this.rightPaddle.y &&
                this.ball.y <= this.rightPaddle.y + this.rightPaddle.height) {

                double theta = calculateVelocityAngle(rightPaddle);
                double newVx = (Math.cos(theta));
                double newVy = (-Math.sin(theta));

                double oldSign = Math.signum(vx);
                this.vx = newVx*-1*oldSign;
                this.vy = newVy;
            } else if (this.ball.x + this.ball.width > this.rightPaddle.x + this.rightPaddle.width) {
                System.out.println("AI has lost a point");
            }
        }

        if (vy > 0) {
            if (this.ball.y + this.ball.width > Constants.SCREEN_HEIGHT) {
                this.vy *= -1;
            }
        } else if (vy < 0) {
            if (this.ball.y < Constants.TOOLBAR_HEIGHT) {
                this.vy *= -1;
            }
        }

        this.ball.x += vx*Constants.BALL_HSPEED*dt;
        this.ball.y += vy*Constants.BALL_VSPEED*dt;

        if (this.ball.x < 0) {
            rightScore++;
            this.ball.y = Constants.SCREEN_HEIGHT/2;
            this.ball.x = Constants.SCREEN_WIDTH/2;
            this.vx = -1;
            this.vy = 1;

            if (rightScore >= Constants.WIN_CONDITION) {
                Main.changeState(0);
            }

        } else if (this.ball.x > Constants.SCREEN_WIDTH) {
            leftScore++;
            this.ball.y = Constants.SCREEN_HEIGHT/2;
            this.ball.x = Constants.SCREEN_WIDTH/2;
            this.vx = 1;
            this.vy = 1;

            if (leftScore >= Constants.WIN_CONDITION) {
                Main.changeState(0);
            }

        }
    }
}
