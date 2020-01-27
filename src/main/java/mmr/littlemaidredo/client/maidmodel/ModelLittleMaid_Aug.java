package mmr.littlemaidredo.client.maidmodel;



/**
 * サンプルとしてaddPartsを使用しています。
 */
public class ModelLittleMaid_Aug extends ModelLittleMaid_SR2 {
	
	public MaidRendererModel shaggyB;
	public MaidRendererModel shaggyR;
	public MaidRendererModel shaggyL;
	
	public MaidRendererModel SideTailR;
	public MaidRendererModel SideTailL;

	public MaidRendererModel sensor1;
	public MaidRendererModel sensor2;
	public MaidRendererModel sensor3;
	public MaidRendererModel sensor4;

	
	public ModelLittleMaid_Aug() {
		super();
	}
	public ModelLittleMaid_Aug(float psize) {
		super(psize);
	}
	public ModelLittleMaid_Aug(float psize, float pyoffset, int pTextureWidth, int pTextureHeight) {
		super(psize, pyoffset, pTextureWidth, pTextureHeight);
	}


	@Override
	public void initModel(float psize, float pyoffset) {
		super.initModel(psize, pyoffset);

		//TODO

		// 再構成パーツ
		SideTailR = new MaidRendererModel(this);
		SideTailR.setTextureOffset(46, 20).addBox( -1.5F, -0.5F, -1.0F, 2, 10, 2, psize);
		SideTailR.setRotationPoint(-5F, -7.8F, 1.9F);
		SideTailL = new MaidRendererModel(this);
		SideTailL.setTextureOffset(54, 20).addBox( 0.5F, -0.5F, -1.0F, 2, 10, 2, psize);
		SideTailL.setRotationPoint(4F, -7.8F, 1.9F);
		
		
		// 増加パーツ
		shaggyB = new MaidRendererModel(this, 24, 0);
		shaggyB.addBox( -5.0F, 0.0F, 0.0F, 10, 4, 4, psize);
		shaggyB.setRotationPoint(0.0F, -1.0F, 4.0F);
		shaggyB.setRotateAngleX(0.4F);
		shaggyR = new MaidRendererModel(this, 34, 4);
		shaggyR.addBox( 0.0F, 0.0F, -5.0F, 10, 4, 1, psize);
		shaggyR.setRotationPoint(4.0F, -1.0F, 0.0F);
		shaggyR.setRotateAngleZ(-0.4F);
		shaggyL = new MaidRendererModel(this, 24, 4);
		shaggyL.addBox( 0.0F, 0.0F, -5.0F, 10, 4, 5, psize);
		shaggyL.setRotationPoint(-4.0F, -1.0F, 0.0F);
		shaggyL.setRotateAngleZ(0.4F);
		
		sensor1 = new MaidRendererModel(this, 0, 0);
		sensor1.addBox( -8.0F, -4.0F, 0.0F, 8, 4, 0);
		sensor1.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
		sensor2 = new MaidRendererModel(this, 0, 4);
		sensor2.addBox( 0.0F, -4.0F, 0.0F, 8, 4, 0);
		sensor2.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
		sensor3 = new MaidRendererModel(this, 44, 0);
		sensor3.addBox( 0.0F, -7.0F, -4.0F, 4, 8, 1);
		sensor3.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
		sensor4 = new MaidRendererModel(this, 34, 0);
		sensor4.addBox( 0.0F, -4.0F, -10.0F, 10, 4, 1);
		sensor4.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
		
		
		// 変更パーツ

		bipedHead.setMirror(false);
		bipedHead.setTextureOffset( 0,  0).addBox( -4F, -8F, -4F, 8, 8, 8, psize);		// Head
		bipedHead.setTextureOffset( 0, 18).addBox( -5F, -8.5F, 0.2F, 1, 3, 3, psize);	// ChignonR
		bipedHead.setTextureOffset(24, 18).addBox( 4F, -8.5F, 0.2F, 1, 3, 3, psize);		// ChignonL
		bipedHead.setTextureOffset(52, 10).addBox( -7.5F, -9.5F, 0.9F, 4, 3, 2, psize);	// sidetailUpperR
		bipedHead.setTextureOffset(52, 15).addBox( 3.5F, -9.5F, 0.9F, 4, 3, 2, psize);	// sidetailUpperL
		bipedHead.setRotationPoint(0F, 0F, 0F);
		
		bipedHead.addChild(HeadMount);
		bipedHead.addChild(HeadTop);
		bipedHead.addChild(SideTailR);
		bipedHead.addChild(SideTailL);
		bipedHead.addChild(shaggyB);
		bipedHead.addChild(shaggyR);
		bipedHead.addChild(shaggyL);
		bipedHead.addChild(sensor1);
		bipedHead.addChild(sensor2);
		bipedHead.addChild(sensor3);
		bipedHead.addChild(sensor4);
		bipedHead.addChild(eyeR);
		bipedHead.addChild(eyeL);
		
	}

	@Override
	public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3, float pRenderPartialTicks) {
		super.setLivingAnimations(pEntityCaps, par2, par3, pRenderPartialTicks);
		
		float f3 = entityTicksExisted + pRenderPartialTicks + entityIdFactor;
		float f4;
		if (ModelCapsHelper.getCapsValueBoolean(pEntityCaps, caps_isLookSuger)) {
			f3 *= 8.0F;
			f4 = -0.2F;
		} else {
			f4 = (1F - ModelCapsHelper.getCapsValueInt(pEntityCaps, caps_health) / 20F) * 0.5F;
		}
		float f5 = mh_sin(f3 * 0.067F) * 0.05F - f4;
		float f6 = 40.0F / 57.29578F;
		sensor1.setRotateAngle(f5, -f6, f5);
		sensor2.setRotateAngle(-f5, f6, -f5);
		sensor3.setRotateAngle(mh_sin(f3 * 0.067F) * 0.05F - 1.2F - f4, mh_sin(f3 * 0.09F) * 0.4F, mh_cos(f3 * 0.09F) * 0.2F);
		sensor4.setRotateAngle(mh_sin(f3 * 0.067F) * 0.05F + f4, mh_cos(f3 * 0.09F) * 0.5F, mh_sin(f3 * 0.09F) * 0.2F);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float pTicksExisted,
			float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps) {
		super.setRotationAngles(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
		
		SideTailR.setRotateAngleX(SideTailL.setRotateAngleX(bipedHead.getRotateAngleX() * -0.666666666F));
	}

}
