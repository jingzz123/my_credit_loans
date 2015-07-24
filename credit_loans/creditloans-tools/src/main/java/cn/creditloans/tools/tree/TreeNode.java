package cn.creditloans.tools.tree;

public class TreeNode {
	private Integer id;
	
	private Integer pId;
	
	private String name;
	
	private boolean checked;
	
	private boolean open;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public TreeNode(Integer id, Integer pId, String name, Boolean checked, Boolean open) {  
        super();  
        this.id = id;  
        this.pId = pId;  
        this.name = name;  
        this.checked = checked;  
        this.open = open;  
    }
	
    public TreeNode() {  
        super();  
    }
}
